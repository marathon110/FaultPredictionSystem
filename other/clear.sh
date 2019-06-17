#!/bin/bash

rm -rf data/one_day_out


# 删除mysql中所有表
# 示例：
# Usage: ./script user password dbnane
# Usage: ./script user password dbnane server-ip
# Usage: ./script user password dbnane mysql.nixcraft.in
# ---------------------------------------------------
 
MUSER="root"
MPASS="123456"
MDB="graduation"
 
MHOST="localhost"
 
# 设置命令路径
MYSQL=$(which mysql)
AWK=$(which awk)
GREP=$(which grep)

 
# 连接mysql数据库
$MYSQL -u $MUSER -p$MPASS -h $MHOST -e "use $MDB"  &>/dev/null
if [ $? -ne 0 ]
then
 echo "Error - 用户名或密码无效，无法连接mysql数据库"
 exit 2
fi
 
# TABLES=$($MYSQL -u $MUSER -p$MPASS -h $MHOST $MDB -e 'show tables' | $AWK '{ print $1}' | $GREP -v '^Tables' )
 
# make sure tables exits
# if [ "$TABLES" == "" ]
# then
#  echo "Error - 在数据库中 $MDB 未发现相关表"
#  exit 3
# fi
 
# let us do it
# for t in $TABLES
# do
#  echo "Truncate $t table from $MDB database..."
#  $MYSQL -u $MUSER -p$MPASS -h $MHOST $MDB -e "TRUNCATE TABLE  $t"
# done

echo "Truncate $t table from $MDB database..."
$MYSQL -u $MUSER -p$MPASS -h $MHOST $MDB -e "TRUNCATE TABLE predict"