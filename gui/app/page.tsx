"use client";

import Input from "./components/Input/Input";
import Button from "./components/Button/Button";
import ThemeSwitcher from "./components/ThemeSwitcher/ThemeSwitcher";
import { useEffect, useState } from "react";
import net from "net";

export default function Index() {
    const [username, setUsername] = useState<string>("");
    const [password, setPassword] = useState<string>("");
    const [client, setClient] = useState<net.Socket>();

    return (
        <main className="w-screen h-screen bg-white dark:bg-gray-800">
            <ThemeSwitcher></ThemeSwitcher>
            <form>
                <Input
                    type="text"
                    placeholder="Username"
                    onChange={(event) => {
                        setUsername(event.target.value);
                    }}
                />
                <Input
                    type="password"
                    placeholder="Password"
                    onChange={(event) => {
                        setPassword(event.target.value);
                    }}
                />
                <label className="text-sm font-medium text-gray-300">
                    Do not have account?{" "}
                    <a
                        href="#"
                        className="text-blue-600 hover:underline dark:text-blue-500"
                    >
                        Sign up here!
                    </a>
                </label>
                <Button
                    text="Sign in"
                    onClick={(event: React.MouseEvent<HTMLButtonElement>) => {
                        event.preventDefault();
                        console.log(username, password);
                    }}
                />
            </form>
        </main>
    );
}
