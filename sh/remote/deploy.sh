#! /bin/bash                                                                                                                  

# some var                                                                                                                    
server_list=server.ini
param=
item_name=
user=
outer_host=
local_host=
program_type=
base_dir=
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
        war_or_jar=$(echo $LINE | awk '{print $8}')
        echo "$0 $param ==>>> deploy to [$outer_host ($local_host)] $war_or_jar "
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
local_jar_or_war=$(echo $LINE | awk '{print $9}')


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


to=$from


command="scp"

date_str=`date "+%Y%m%d-%H:%M:%S"`


echo $dash_line
echo $dash_line
echo "---- DEPLOY BEGIN -----"
echo

ssh $user@${outer_host} <<EOF
echo "...begin to backup..."
cp ${from}/$war_or_jar ${to}/${war_or_jar}${date_str}
echo "...backup end..."
exit
EOF

$command $local_jar_or_war $user@$outer_host:${from}/$war_or_jar

echo
echo "---- DEPLOY END -----"
echo $dash_line
echo $dash_line

exit 0
