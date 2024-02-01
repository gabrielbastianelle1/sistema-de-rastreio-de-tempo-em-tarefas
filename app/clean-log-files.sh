#!/bin/bash

# Specify the folder path
folder_path="."

# Monitor the folder for create events on .log files
inotifywait -m -e create --format '%w%f' "$folder_path" |
while read -r new_log_file
do
    # Delete the newly created .log file
    rm "$new_log_file"
done
