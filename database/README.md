docker compose up -d
docker exec -it fitness-postgres psql -U fitness -d fitnessdb

docker ps
\dt
\q
