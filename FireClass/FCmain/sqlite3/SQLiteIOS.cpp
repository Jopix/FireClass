#include <map>
#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
#if FC_PLATFORM == FC_WIN
#include "sqlite3\include\sqlite3.h"
#else
#include <sqlite3.h>
#endif

static int _initialized = 0;
static sqlite3 *_db;
static std::map<const char*, bool> _table_exist_map;
static std::map<const char*, sqlite3_stmt*> _stmt_select_map;
static std::map<const char*, sqlite3_stmt*> _stmt_update_map;
static std::map<const char*, sqlite3_stmt*> _stmt_remove_map;

// SELECT
const char *sql_select_fmt = "SELECT value FROM %s WHERE key=?;";
// REPLACE
const char *sql_update_fmt = "REPLACE INTO %s (key, value) VALUES (?,?);";
// DELETE
const char *sql_remove_fmt = "DELETE FROM %s WHERE key=?;";

#define ITERATOR_FINALIZE(_MAP) \
for(std::map<const char*, sqlite3_stmt*>::iterator iter = _MAP.begin() ; iter != _MAP.end() ; ++ iter){ \
	sqlite3_finalize(iter->second); \
} \
_MAP.clear();


static void sqlCheckTable(const char* tableName)
{
	if (true != _table_exist_map[tableName]) {
		char sql_buffer[512];
		const char *sql_createtable_fmt = "CREATE TABLE IF NOT EXISTS %s(key TEXT PRIMARY KEY,value TEXT);";
		sprintf(sql_buffer, sql_createtable_fmt, tableName);

		sqlite3_stmt *stmt;
		int ok=sqlite3_prepare_v2(_db, sql_buffer, -1, &stmt, NULL);
		ok |= sqlite3_step(stmt);
		ok |= sqlite3_finalize(stmt);
		
		if( ok != SQLITE_OK && ok != SQLITE_DONE)
			printf("Error in CREATE TABLE\n");

		sqlite3_stmt *select_stmt;
		sprintf(sql_buffer, sql_select_fmt, tableName);
		ok = sqlite3_prepare_v2(_db, sql_buffer, -1, &select_stmt, NULL);
		_stmt_select_map[tableName] = select_stmt;

		sqlite3_stmt *update_stmt;
		sprintf(sql_buffer, sql_update_fmt, tableName);
		ok = sqlite3_prepare_v2(_db, sql_buffer, -1, &update_stmt, NULL);
		_stmt_update_map[tableName] = update_stmt;

		sqlite3_stmt *remove_stmt;
		sprintf(sql_buffer, sql_remove_fmt, tableName);
		ok = sqlite3_prepare_v2(_db, sql_buffer, -1, &remove_stmt, NULL);
		_stmt_remove_map[tableName] = remove_stmt;

		_table_exist_map[tableName] = true;
	}
}

void sqlInit(const char *fullpath)
{
	if( ! _initialized ) {

		int ret = 0;
		
		if (!fullpath)
			ret = sqlite3_open(":memory:",&_db);
		else
			ret = sqlite3_open(fullpath, &_db);
		
		_initialized = 1;
	}
}

void sqlFree()
{
	if( _initialized ) {
		ITERATOR_FINALIZE(_stmt_select_map);
		ITERATOR_FINALIZE(_stmt_remove_map);
		ITERATOR_FINALIZE(_stmt_update_map);
		_table_exist_map.clear();
		sqlite3_close(_db);
		
		_initialized = 0;
	}
}

/** sets an item in the SQLite */
void sqlSetItem(const char *tableName, const char *key, const char *value)
{
	assert( _initialized );
	
	sqlCheckTable(tableName);

	int ok = sqlite3_bind_text(_stmt_update_map[tableName], 1, key, -1, SQLITE_TRANSIENT);
	ok |= sqlite3_bind_text(_stmt_update_map[tableName], 2, value, -1, SQLITE_TRANSIENT);

	ok |= sqlite3_step(_stmt_update_map[tableName]);
	
	ok |= sqlite3_reset(_stmt_update_map[tableName]);
	
	if( ok != SQLITE_OK && ok != SQLITE_DONE)
		printf("Error in sql.setItem()\n");
}

/** gets an item from the SQLite */
const char* sqlGetItem(const char *tableName, const char *key )
{
	assert( _initialized );

	sqlCheckTable(tableName);

	int ok = sqlite3_reset(_stmt_select_map[tableName]);

	ok |= sqlite3_bind_text(_stmt_select_map[tableName], 1, key, -1, SQLITE_TRANSIENT);
	ok |= sqlite3_step(_stmt_select_map[tableName]);
	const unsigned char *ret = sqlite3_column_text(_stmt_select_map[tableName], 0);
	

	if( ok != SQLITE_OK && ok != SQLITE_DONE && ok != SQLITE_ROW)
		printf("Error in sql.getItem()\n");

	return (const char*)ret;
}

/** removes an item from the SQLite */
void sqlRemoveItem(const char *tableName, const char *key )
{
	assert( _initialized );

	sqlCheckTable(tableName);

	int ok = sqlite3_bind_text(_stmt_remove_map[tableName], 1, key, -1, SQLITE_TRANSIENT);
	
	ok |= sqlite3_step(_stmt_remove_map[tableName]);
	
	ok |= sqlite3_reset(_stmt_remove_map[tableName]);

	if( ok != SQLITE_OK && ok != SQLITE_DONE)
		printf("Error in sql.removeItem()\n");
}

void sqlExec(const char* sql)
{
	assert( _initialized );
	
	sqlite3_stmt *stmt;
	int ok=sqlite3_prepare_v2(_db, sql, -1, &stmt, NULL);
	ok |= sqlite3_step(stmt);
	ok |= sqlite3_finalize(stmt);
}
