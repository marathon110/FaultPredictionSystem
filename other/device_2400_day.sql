
drop table graduation.device_2400_day;

create table graduation.device_2400_day(
date_str varchar(40),
net_droppedTx_summation varchar(40),
net_packetsTx_summation varchar(40),
virtualDisk_numberReadAveraged_average varchar(40),
virtualDisk_numberWriteAveraged_average varchar(40),
virtualDisk_write_average varchar(40),
virtualDisk_read_average varchar(40),
datastore_maxTotalLatency_latest varchar(40),
cpu_usage_average varchar(40),
mem_usage_average varchar(40),
net_usage_average varchar(40),
net_packetsRx_summation varchar(40),
net_droppedRx_summation varchar(40),
net_bytesRx_average varchar(40),
net_bytesTx_average varchar(40)
) CHARSET = utf8;


LOAD DATA INFILE '/var/lib/mysql-files/a.csv' INTO TABLE graduation.device_2400_day FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n';
select * from graduation.device_2400_day;



---

create table graduation.device_7361_day(
date_str varchar(40),
net_droppedTx_summation varchar(40),
net_packetsTx_summation varchar(40),
virtualDisk_numberReadAveraged_average varchar(40),
virtualDisk_numberWriteAveraged_average varchar(40),
virtualDisk_write_average varchar(40),
virtualDisk_read_average varchar(40),
datastore_maxTotalLatency_latest varchar(40),
cpu_usage_average varchar(40),
mem_usage_average varchar(40),
net_usage_average varchar(40),
net_packetsRx_summation varchar(40),
net_droppedRx_summation varchar(40),
net_bytesRx_average varchar(40),
net_bytesTx_average varchar(40)
) CHARSET = utf8;


LOAD DATA INFILE '/var/lib/mysql-files/b.csv' INTO TABLE graduation.device_7361_day FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n';
select * from graduation.device_7361_day;



---
create table graduation.device_7363_day(
date_str varchar(40),
net_droppedTx_summation varchar(40),
net_packetsTx_summation varchar(40),
virtualDisk_numberReadAveraged_average varchar(40),
virtualDisk_numberWriteAveraged_average varchar(40),
virtualDisk_write_average varchar(40),
virtualDisk_read_average varchar(40),
datastore_maxTotalLatency_latest varchar(40),
cpu_usage_average varchar(40),
mem_usage_average varchar(40),
net_usage_average varchar(40),
net_packetsRx_summation varchar(40),
net_droppedRx_summation varchar(40),
net_bytesRx_average varchar(40),
net_bytesTx_average varchar(40)
) CHARSET = utf8;


LOAD DATA INFILE '/var/lib/mysql-files/c.csv' INTO TABLE graduation.device_7363_day FIELDS TERMINATED BY ',' LINES TERMINATED BY '\n';
select * from graduation.device_7363_day;
