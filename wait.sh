#!/bin/sh
# wait-for-postgres.sh

set -e
  
host="$1"
shift
cmd="$@"
  
until PGPASSWORD="test" psql -h "$host" -U "test" -c '\q'; do
  >&2 echo "Postgres is unavailable - sleeping"
  sleep 1
done
  
>&2 echo "Postgres is up - executing command"
exec $cmd