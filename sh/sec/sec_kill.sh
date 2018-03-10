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
		echo "$0 $param [date] =========>>> kill  $host $item_name "
	done <$server_list
	exit 0
fi

LINE=`grep "^$1 " $server_list`
if [ -z "$LINE" ] ; then 
	echo "bad parameter!"
	echo "please $0  or $0 help  to get help"
	exit 0
fi


#param=$(echo $LINE | awk '{print $1}')			
#war_name=$(echo $LINE | awk '{print $2}')
host=$(echo $LINE | awk '{print $4}')
item_name=$(echo $LINE | awk '{print $3}')
user=$(echo $LINE | awk '{print $5}')

ssh $user@${host} <<EOF
echo "kill ${host} ${item_name}" && \
ps x | grep jboss | grep ${item_name}  | grep -v grep | awk '{print \$1}' | xargs -t kill -9
echo "kill finished ,please check!"
exit

exit 0
