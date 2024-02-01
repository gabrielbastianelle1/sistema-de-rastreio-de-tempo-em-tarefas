#!/bin/bash

# Specify the folder path
folder_path="."

# Run an infinite loop to continuously search for and delete .log files
while true
do
    # Find and delete .log files in the specified folder
    find "$folder_path" -name "*.log" -type f -delete

    # Sleep for 10 seconds
    sleep 10
done