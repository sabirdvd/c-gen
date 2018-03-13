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
		echo "$0 $param [date] =========>>> see $host $item_name real-time log"
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

ssh -tt $user@${host} tail -f /opt/redhat/jboss-eap-5.1/jboss-as/server/${item_name}/log/server.log

echo "------------------------------------------------------------------"
echo "----------You Already Out??---------------------------------------"
echo "------------------------------------------------------------------"
exit 0
