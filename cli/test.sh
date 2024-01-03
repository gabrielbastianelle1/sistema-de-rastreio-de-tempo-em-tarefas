for i in {0..255}; do
    printf "\x1b[48;5;%sm  " "$i"
    printf "  \x1b[38;5;%sm%3d\e[0m  " "$i" "$i"
    printf "  \x1b[38;5;%sm0x%02X\e[0m\n" "$i" "$i"
done
