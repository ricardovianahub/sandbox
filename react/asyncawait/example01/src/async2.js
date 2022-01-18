let value = "initial"

const call = (message) => new Promise((resolve, reject) => {
    value = "[VALUE] " + message;
    resolve(message);
})

// --------------------------------

const run = (letter) => {
    console.log("0 - main:", value);
    call(letter + "01").then(
        (message) => console.log("1 - then:", value, "- should be", message)
    )
    console.log("  - main:", value);
    call(letter + "02").then(
        (message) => console.log("2 - then:", value, "- should be", message)
    )
    console.log("  - main:", value);
    call(letter + "03").then(
        (message) => console.log("2 - then:", value, "- should be", message)
    )
    console.log("  - main:", value);
}

run("A");
console.log("9 - main:", value);
console.log("------------------------------------------------------------")
