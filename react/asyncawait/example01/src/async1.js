const giveMeAPromise = (message) =>
    new Promise(((resolve, reject) => {
        console.log("[inside] running - message: ", message);
        resolve("[first] executed - message: " + message);
    }))

const resolve = (message) => {
    console.log("[resolve]", message);
}

const runAsyncAwait = async (id) => {
    await giveMeAPromise(id + "01").then(resolve);
    await giveMeAPromise(id + "02").then(resolve);
    await giveMeAPromise(id + "03").then(resolve);
    await giveMeAPromise(id + "04").then(resolve);
}
const runThen = (id) => {
    giveMeAPromise(id + "01").then((message) => {
            resolve(id + "01");
            giveMeAPromise(id + "02").then(() => {
                resolve(id + "02");
                giveMeAPromise(id + "03").then(() => {
                    resolve(id + "03");
                    giveMeAPromise(id + "04").then(() => {
                        resolve(id + "04");
                    })
                })
            })
        }
    );
}

runThen("X")
// runThen("Y")
console.log("============================================================")
// runAsyncAwait("A")
// runAsyncAwait("B")
// console.log("============================================================")
