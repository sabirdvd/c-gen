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
        echo "$0 $param ======>>> kill [$outer_host ($local_host)] $item_name "

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
#echo "$0 $param ======>>> kill [$outer_host ($local_host)] $item_name "

echo $dash_line
echo "kill [${outer_host} (${local_host})] ${item_name} BEGIN..."
pid=`ps x | grep java | grep ${item_name}  | grep -v grep | awk '{print $1}'`
echo "pid = $pid"

if [ -z "$pid" ] ; then
    echo "${item_name} is not running..., Please Check!!!"
    echo $dash_line
    exit 0
fi

ps x | grep java | grep "${item_name}"  | grep -v grep | awk '{print $1}' | xargs -t kill -9
echo "kill END..."
echo "kill finished ,please check!"
echo $dash_line
exit 0
