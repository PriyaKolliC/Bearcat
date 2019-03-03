run:
	export CLOUD_SQL_CONNECTION_NAME="destress:us-central1:mysql" && \
	export DB_USER="root" && \
	export DB_PASS="qwertyui" && \
	export DB_NAME="app_database" && \
	python main.py

start-cloud-sql-proxy: 
	export CLOUD_SQL_CONNECTION_NAME="destress:us-central1:mysql" && \
	export GOOGLE_APPLICATION_CREDENTIALS="keys/cloud-sql-user.json" && \
	./cloud_sql_proxy -dir=/cloudsql --instances=$(CLOUD_SQL_CONNECTION_NAME) --credential_file=$(GOOGLE_APPLICATION_CREDENTIALS)

start-cloud-sql-proxy-background:
	export CLOUD_SQL_CONNECTION_NAME="destress:us-central1:mysql" && \
	export GOOGLE_APPLICATION_CREDENTIALS="keys/cloud-sql-user.json" && \
	./cloud_sql_proxy -dir=/cloudsql --instances=$(CLOUD_SQL_CONNECTION_NAME) --credential_file=$(GOOGLE_APPLICATION_CREDENTIALS) &

export-environment-vars:
	
