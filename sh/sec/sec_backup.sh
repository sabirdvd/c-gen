#! /bin/bash

# some var
server_list=server.ini
param=
host=
item_name=
user=

# help
if [ -z "$1" ] || [ "$1" = "help" ] ; then
	while read LINE
	do
		param=$(echo $LINE | awk '{print $1}')			
		host=$(echo $LINE | awk '{print $4}')
		item_name=$(echo $LINE | awk '{print $3}')
		echo "$0 $param =========>>> full backup $host $item_name"
	done <$server_list
	exit 0
fi

LINE=`grep "^$1 " $server_list`
if [ -z "$LINE" ] ; then 
	echo "bad parameter!"
	echo "please $0  or $0 help  to get help"
	exit 0
fi


param=$(echo $LINE | awk '{print $1}')			
war_name=$(echo $LINE | awk '{print $2}')
host=$(echo $LINE | awk '{print $4}')
item_name=$(echo $LINE | awk '{print $3}')
user=$(echo $LINE | awk '{print $5}')
backup_dir=/data/deploy/ # remote bakup base directory
from=/opt/redhat/jboss-eap-5.1/jboss-as/server/${item_name}/deploy/Gome${war_name}.war # where deployed


# ready for bakup
bak_date=$(date +'%Y%m%d') # this time as the directory name


echo "+--------------------------------------------------------------------------+"
echo "+   Begin FullBakup ${host} ${item_name} , Please Wait A Minute!          +"
echo "+--------------------------------------------------------------------------+"

to=${backup_dir}/gome${war_name}-${bak_date} # local bakup directory
ssh $user@${host} <<EOF
echo "now...." && \
mkdir -p ${to}
cp -r ${from} ${to}
exit
EOF

echo "+----------------------------------------------+"
echo "+   Full Backup  Finished!Thank You ! -_-            +"
echo "+----------------------------------------------+"

exit 0
