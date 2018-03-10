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
		echo "$0 $param bugId =========>>> update to  $host $item_name"
	done <$server_list
	exit 0
fi

LINE=`grep "^$1 " $server_list`
if [ -z "$LINE" ] ; then 
	echo "bad parameter!"
	echo "please $0  or $0 help  to get help"
	exit 0
fi

if [ -z "$2" ] ; then
	echo " You must Input BugId as the second parameter "
	exit 0
fi

param=$(echo $LINE | awk '{print $1}')			
war_name=$(echo $LINE | awk '{print $2}')
update_files=Gome${war_name}.war # updatefiles

echo "update_files= $update_files"

# if the updatefiles does't existed
if [ ! -d $update_files ]; then
	echo "+---------------------------------------------------------------------------+"
	echo "+  Warning! Warning!The ${update_files} Fiels Do No Exited,Please Check!    +"
	echo "+---------------------------------------------------------------------------+"
	exit 1
fi

temp_list="temp.ini"
find $update_files -type f > $temp_list
bak_date=$(date +'%Y-%m-%d@%H:%m:%S')

host=$(echo $LINE | awk '{print $4}')
item_name=$(echo $LINE | awk '{print $3}')
user=$(echo $LINE | awk '{print $5}')
# back up to local
local_bak_dir=$(basename `pwd`)_backup/${bak_date}_$2_${param}
mkdir -p $local_bak_dir 
cp $temp_list $local_bak_dir 
remote_server_dir=/opt/redhat/jboss-eap-5.1/jboss-as/server/${item_name}/deploy # where deployed

echo "+-----------------------------------------------------------+"
echo "+  Begin Backup To Local, Please Waite A Minute...        +"
echo "+-----------------------------------------------------------+"
while read LINE
do
	file_dir_name=$(echo $LINE)
	file_dir=$(dirname $file_dir_name)
	file_name=$(basename $file_dir_name)
		
	# make the needed directory
	temp_dir=$local_bak_dir/$file_dir
	if [ ! -d $temp_dir ]; then
		mkdir -p $temp_dir
	fi
	scp $user@$host:$remote_server_dir/$file_dir_name $temp_dir
done <$temp_list

# back up to server
echo "+-----------------------------------------------------------+"
echo " Begin Backup To Server, Please Waite A Minute..."
echo "+-----------------------------------------------------------+"
scp -r $local_bak_dir $user@$host:/data/auto_deploy_backup
# update to server
echo "+-----------------------------------------------------------+"
echo " Begin update ..."
echo "+-----------------------------------------------------------+"
scp -r $update_files $user@$host:$remote_server_dir

echo " update done"
exit 0
