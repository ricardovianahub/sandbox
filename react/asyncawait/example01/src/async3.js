let value = "initial"

const call = (message) => new Promise((resolve, reject) => {
    setTimeout(() => {
        value = "[VALUE] " + message;
        resolve(message);
    }, 200);
})

// --------------------------------

const run = (letter) => {
    console.log("0 - main: " + value);
    call(letter + "01").then((message) => {
            console.log("1 - then:", value, "- should be", message)
            console.log("5 - main:", value);
            call(letter + "02").then((message) => {
                    console.log("3 - then:", value, "- should be", message);
                    console.log("9 - main:", value);
                }
            )
        }
    )
}

run("A");
run("B");
console.log("------------------------------------------------------------")
