import { useEffect, useState } from "react";
import Button from "../Button/Button";

export default function ThemeSwitcher() {
    const [darkMode, setDarkMode] = useState<boolean>(false);

    useEffect(() => {
        const isDarkMode = localStorage.getItem("darkMode") === "true";
        setDarkMode(isDarkMode);
    }, []);

    useEffect(() => {
        document.documentElement.classList.toggle("dark", darkMode);
        localStorage.setItem("darkMode", darkMode.toString());
    }, [darkMode]);

    const toggleDarkMode = () => {
        setDarkMode((prevMode) => !prevMode);
    };

    return (
        <>
            {/* <svg
                className="bg-red-500 h-12 w-12 fill-black"
                viewBox="0 0 1548 1549"
            >
                <path>
                    <animate
                        attributeName="d"
                        dur="1000ms"
                        repeatCount="indefinite"
                        values="M1282.24,1296.57c-136.765,133.071 -317.217,206.368 -508.104,206.368c-401.86,0 -728.81,-326.935 -728.81,-728.809c0,-401.874 326.95,-728.81 728.81,-728.81c13.675,0 27.433,0.386 41.149,1.158c-106.851,99.393 -167.217,237.014 -167.217,384.794c-0,290.322 236.186,526.508 526.494,526.508c118.845,-0 232.437,-39.564 324.91,-112.338c-16.584,171.271 -92.639,329.941 -217.232,451.129m250.013,-519.326c-7.816,-3.487 -16.97,-1.888 -23.159,4.026c-90.584,86.6 -209.388,134.298 -334.532,134.298c-267.038,-0 -484.283,-217.232 -484.283,-484.297c-0,-151.806 69.258,-291.962 190.019,-384.518c6.796,-5.197 9.774,-14.02 7.527,-22.277c-2.247,-8.272 -9.264,-14.351 -17.756,-15.412c-31.72,-3.943 -64.006,-5.942 -95.933,-5.942c-425.144,0 -771.007,345.877 -771.007,771.007c0,425.13 345.863,770.993 771.007,770.993c201.915,0 392.83,-77.529 537.536,-218.307c144.417,-140.473 227.198,-328.603 233.099,-529.678c0.248,-8.561 -4.687,-16.419 -12.518,-19.893;

                        M774.125,1332.48c-308.369,-0 -558.353,-249.984 -558.353,-558.353c0,-308.369 249.984,-558.353 558.353,-558.353c308.369,0 558.353,249.984 558.353,558.353c-0,308.369 -249.984,558.353 -558.353,558.353Zm710.409,-258.248c-133.415,-140.064 -116.809,-218.147 60.591,-300.105c-177.4,-81.958 -194.006,-160.041 -60.591,-300.113c-193.494,-1.965 -236.958,-65.919 -165.229,-245.067c-179.148,71.729 -243.102,28.265 -245.075,-165.229c-140.064,133.415 -218.147,116.809 -300.105,-60.591c-81.958,177.4 -160.041,194.006 -300.105,60.591c-1.973,193.494 -65.927,236.958 -245.075,165.229c71.729,179.148 28.265,243.102 -165.229,245.067c133.415,140.072 116.809,218.155 -60.591,300.113c177.4,81.958 194.006,160.041 60.591,300.105c193.494,1.965 236.958,65.927 165.229,245.075c179.148,-71.729 243.102,-28.265 245.075,165.229c140.064,-133.415 218.147,-116.809 300.105,60.591c81.958,-177.4 160.041,-194.006 300.105,-60.591c1.973,-193.494 65.927,-236.958 245.075,-165.229c-71.729,-179.148 -28.265,-243.11 165.229,-245.075Z;

                        "
                    />
                </path>
            </svg>

            <svg
                className="bg-red-500 h-12 w-12 fill-yellow-500"
                viewBox="0 0 1548 1549"
            >
                <path
                    id="sun"
                    d="M774.125,1332.48c-308.369,-0 -558.353,-249.984 -558.353,-558.353c0,-308.369 249.984,-558.353 558.353,-558.353c308.369,0 558.353,249.984 558.353,558.353c-0,308.369 -249.984,558.353 -558.353,558.353Zm710.409,-258.248c-133.415,-140.064 -116.809,-218.147 60.591,-300.105c-177.4,-81.958 -194.006,-160.041 -60.591,-300.113c-193.494,-1.965 -236.958,-65.919 -165.229,-245.067c-179.148,71.729 -243.102,28.265 -245.075,-165.229c-140.064,133.415 -218.147,116.809 -300.105,-60.591c-81.958,177.4 -160.041,194.006 -300.105,60.591c-1.973,193.494 -65.927,236.958 -245.075,165.229c71.729,179.148 28.265,243.102 -165.229,245.067c133.415,140.072 116.809,218.155 -60.591,300.113c177.4,81.958 194.006,160.041 60.591,300.105c193.494,1.965 236.958,65.927 165.229,245.075c179.148,-71.729 243.102,-28.265 245.075,165.229c140.064,-133.415 218.147,-116.809 300.105,60.591c81.958,-177.4 160.041,-194.006 300.105,-60.591c1.973,-193.494 65.927,-236.958 245.075,-165.229c-71.729,-179.148 -28.265,-243.11 165.229,-245.075Z"
                />
            </svg>

            <svg>
                <path fill="#1EB287">
                    <animate
                        attributeName="d"
                        dur="1440ms"
                        repeatCount="indefinite"
                        values="M 0,0
                     C 50,0 50,0 100,0
                     100,50 100,50 100,100
                     50,100 50,100 0,100
                     0,50 0,50 0,0
                     Z;

                     M 0,0
                     C 50,0 50,0 100,0
                     100,50 100,50 100,100
                     50,100 50,100 0,100
                     0,50 0,50 0,0
                     Z;

                     M 50,0
                     C 75,25 75,25 100,50
                     75,75 75,75 50,100
                     25,75 25,75 0,50
                     25,25 25,25 50,0
                     Z;

                     M 25,50
                     C 37.5,25 37.5,25 50,0
                     75,50 75,50 100,100
                     50,100 50,100 0,100
                     12.5,75 12.5,75 25,50
                     Z;

                     M 25,50
                     C 37.5,25 37.5,25 50,0
                     75,50 75,50 100,100
                     50,100 50,100 0,100
                     12.5,75 12.5,75 25,50
                     Z;

                     M 50,0
                     C 77.6,0 100,22.4 100,50
                     100,77.6 77.6,100 50,100
                     22.4,100, 0,77.6, 0,50
                     0,22.4, 22.4,0, 50,0
                     Z;

                     M 50,0
                     C 77.6,0 100,22.4 100,50
                     100,77.6 77.6,100 50,100
                     22.4,100, 0,77.6, 0,50
                     0,22.4, 22.4,0, 50,0
                     Z;

                     M 100,0
                     C 100,50 100,50 100,100
                     50,100 50,100 0,100
                     0,50 0,50 0,0
                     50,0 50,0 100,0
                     Z;"
                    />
                    <animate
                        attributeName="fill"
                        dur="1440ms"
                        repeatCount="indefinite"
                        values="#1eb287;
                     #1eb287;
                     #1ca69e;
                     #188fc2;
                     #188fc2;
                     #bb625e;
                     #ca5f52;
                     #1eb287;"
                    />
                </path>
            </svg> */}
            <Button
                text={darkMode ? "Light Mode" : "Dark Mode"}
                onClick={toggleDarkMode}
            />
        </>
    );
}
