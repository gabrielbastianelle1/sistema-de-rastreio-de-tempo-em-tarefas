import type { Config } from "tailwindcss";

const config: Config = {
    content: [
        "./pages/**/*.{js,ts,jsx,tsx,mdx}",
        "./components/**/*.{js,ts,jsx,tsx,mdx}",
        "./app/**/*.{js,ts,jsx,tsx,mdx}",
    ],
    darkMode: "class", // Enables dark mode based on the class applied to the HTML tag
    theme: {
        extend: {
            colors: {
                blue: {
                    light: "#C5DFFF",
                    dark: "#061E3C",
                    hover: "#1057B0",
                },
            },
        },
    },
    plugins: [],
};
export default config;
