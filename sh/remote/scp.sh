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
        echo "$0 $param localfile remoteDir =>>> scp file to [$outer_host ($local_host)] "
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

local=$2
remote=$user@$outer_host:$3
command="scp"

if [ -d $local ]; then
    command="scp -r"
fi

echo $dash_line
echo "$command $local $remote BEGIN..."
$command  $local $remote
echo "$command $local $remote END..."
echo $dash_line

exit 0
