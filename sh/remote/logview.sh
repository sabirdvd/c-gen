#! /bin/bash

# some var
server_list=server.ini
param=
item_name=
user=
outer_host=
local_host=
dash_line="-----------------------------------------------------------------------------"

# help
if [ -z "$1" ] || [ "$1" = "help" ] ; then
    echo
    echo $dash_line
    while read LINE
    do
	param=$(echo $LINE | awk '{print $1}')
	item_name=$(echo $LINE | awk '{print $2}')
	user=$(echo $LINE | awk '{print $3}')
	outer_host=$(echo $LINE | awk '{print $4}')
	local_host=$(echo $LINE | awk '{print $5}')
	echo "$0 $param [|error|tomcat|nohup] =>>> tail [$outer_host ($local_host)] $item_name log"
   done <$server_list
    echo $dash_line
	exit 0
fi

LINE=`grep "^$1 " $server_list`
if [ -z "$LINE" ] ; then
    echo $dash_line
    echo "bad parameter!"
    echo "please $0  or $0 help  to get help"
    echo $dash_line
    exit 0
fi

param=$(echo $LINE | awk '{print $1}')
item_name=$(echo $LINE | awk '{print $2}')
user=$(echo $LINE | awk '{print $3}')
outer_host=$(echo $LINE | awk '{print $4}')
local_host=$(echo $LINE | awk '{print $5}')
program_type=$(echo $LINE | awk '{print $6}')
base_dir=$(echo $LINE | awk '{print $7}')
date_str=`date "+%Y-%m-%d"`


log_type=$2
if [ -z "$log_type" ] ; then
    log_type="info"
fi


file_name=

case $log_type in
    info)
        file_name="/var/log/junyi/${item_name}/${item_name}.log"
        ;;
    error)
        file_name="/var/log/junyi/${item_name}/${item_name}-error.log"
        ;;
    tomcat)
        file_name="${base_dir}/logs/catalina.${date_str}.log"
        ;;
    nohup)
        file_name="${base_dir}/nohup.log"
        ;;

esac


ssh  $user@${outer_host} <<EOF
view $file_name
exit
EOF

echo "------------------------------------------------------------------"
echo "----------You Already Out??---------------------------------------"
echo "------------------------------------------------------------------"
exit 0
