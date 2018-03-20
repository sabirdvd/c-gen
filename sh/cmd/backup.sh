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
	echo "$0 $param ======>>> backup [$outer_host ($local_host)] $item_name "
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
war_or_jar=$(echo $LINE | awk '{print $8}')

from=
to=

case $program_type in
    1)
	from=$base_dir/webapps
	;;
    2)
	from=$base_dir
	;;
esac

echo $dash_line
echo "backup [${outer_host} (${local_host})] ${item_name} BEGIN..."

date_str=`date "+%Y%m%d-%H:%M:%S"`

to=$from/$war_or_jar$date_str
cp -r ${from}/$war_or_jar ${to}
ls -al $from

echo "backup END..."
echo "backup finished ,please check!"
echo $dash_line
exit 0
