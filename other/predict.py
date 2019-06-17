import pandas as pd
import pickle
import pymysql

"""
create database graduation;

create table graduation.predict(
id int primary key auto_increment,
device_id varchar(18),
date varchar(18),
leftcycle varchar(18)
);
"""

def connect_wxremit_db():
    return pymysql.connect(host='localhost',
                           port=3306,
                           user='root',
                           password='123456',
                           database='graduation')

def insert_file_rec(device_id, date, leftcycle):
        con = connect_wxremit_db()
        cur = con.cursor()
        try:
            sql_str = ("INSERT INTO graduation.predict (device_id, date, leftcycle) VALUES ('%s', '%s', '%s')" % (device_id, date, leftcycle))
            cur.execute(sql_str)
            con.commit()
        except:
            con.rollback()
            raise
        finally:
            cur.close()
            con.close()


def main():
	with open('data/one_day_out/part-00000', 'r') as f:
	    lines = f.readlines()
	    samples = []
	    infos = []
	    for l in lines:
	    	s = [float(x) for x in l.split('\n')[0].split(',')[2:]]
	    	i = l.split('\n')[0].split(',')[0:2]
	    	samples.append(s)
	    	infos.append(i)

	print(samples)
	print(infos)

	instances = pd.DataFrame(samples)

	# 加载tree.pickle
	with open('tree.pickle', 'rb') as fr:
	    new_tree = pickle.load(fr)
	    pred_res = new_tree.predict(instances)
	    print(pred_res)

	    for i, l in enumerate(pred_res):
	    	insert_file_rec(infos[i][1], infos[i][0], str(l))

main()