# SpringBoot-Kafka
# CONFIGURACION KAFKA
--LEVANTO EL ZOOKEPER
bin/zookeeper-server-start.sh config/zookeeper.properties
--LUEGO KAFKA
bin/kafka-server-start.sh config/server.properties
--LISTO MI TOPIC CREADO
bin/kafka-topics.sh --list --bootstrap-server 35.188.135.69:9092
-- ESTE COMANDO CREA TOPIC
bin/kafka-topics.sh --create --bootstrap-server 35.188.135.69:9092 --replication-factor 1 --partitions 1 --topic comunicacion
-- INGRESO COMO CONSUMIDOR
bin/kafka-console-consumer.sh --bootstrap-server 35.188.135.69:9092 --topic comunicacion --from-beginning
