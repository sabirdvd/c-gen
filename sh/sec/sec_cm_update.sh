#! /bin/bash

# some var
server_list=server.ini
param=
host=
item_name=
user=

# help
if [ -z "$1" ] || [ "$1" = "help" ] ; then
	echo "update cm files to all "
	exit 0
fi

# bugId
if [ -z "$1" ] ; then
	echo " You must Input BugId as the second parameter "
	exit 0
fi

# cm files
update_files=cm # updatefiles

echo "update_files= $update_files"

# if the updatefiles does't existed
if [ ! -d $update_files ]; then
	echo "+---------------------------------------------------------------------------+"
	echo "+  Warning! Warning!The ${update_files} Fiels Do No Exited,Please Check!    +"
	echo "+---------------------------------------------------------------------------+"
	exit 1
fi

# traversal all servers
while read LINE 
do
	host=$(echo $LINE | awk '{print $4}')
	item_name=$(echo $LINE | awk '{print $3}')
	war_name=$(echo $LINE | awk '{print $2}')
	user=$(echo $LINE | awk '{print $5}')
	remote_server_dir=/opt/redhat/jboss-eap-5.1/jboss-as/server/${item_name}/deploy/Gome${war_name}.war/WEB-INF/classes/com/founder/gome
	# backup
	if [ "Bg" = $war_name ] ; then
			

		echo "+-----------------------------------------------------------+"
		echo "+  Begin Backup To Local, Please Waite A Minute...        +"
		echo "+-----------------------------------------------------------+"

		temp_list="temp.ini"
		find $update_files -type f > $temp_list
		while read LINE2
		do
			file_dir_name=$(echo $LINE2)
			file_dir=$(dirname $file_dir_name)
			file_name=$(basename $file_dir_name)
		
			bak_date=$(date +'%Y-%m-%d@%H:%m:%S')


			# back up to local
			local_bak_dir=$(basename `pwd`)_backup/${bak_date}_${bugid}_${param}
			mkdir -p $local_bak_dir 
			cp $temp_list $local_bak_dir 
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
	fi

	scp $update_files $user@$host:${remote_server_dir}
done < $server_list

echo " update done"
exit 0
