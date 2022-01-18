let value = "initial"

const call = (message) => new Promise((resolve, reject) => {
    setTimeout(() => {
        value = "[VALUE] " + message;
        resolve(message);
    }, 200);
})

// --------------------------------

const run = async (letter) => {
    console.log("0 - main:", value);
    await call(letter + "01").then(
        (message) => {
            value = "inside then";
            console.log("1 - then:", value, "- should be", message)
        }
    )
    console.log("5 - main:", value);
    await call(letter + "02").then(
        (message) => console.log("2 - then:", value, "- should be", message)
    )
    console.log("9 - main:", value);
}

run("A");
console.log("X - main:", value);
run("B");
console.log("------------------------------------------------------------")
