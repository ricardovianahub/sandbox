var endPoint = '/ben/monitor';

function setEndpoint(value) {
    endPoint = value;
}

const App = () => {
    function handleInsertButtonClick() {
        axios.get(endPoint)
            .then(response => {
                document.getElementById("reaccom-message").innerText = "Record inserted succesfully";
                document.querySelectorAll("[data-testid=versionsList]").forEach((versionsList) => {
                    let note: Element = document.createElement("li");
                    let textNode: Element = document.createTextNode(new Date());
                    node.appendChild(textNode);
                    versionsList.appendChild(node);
                })
            })
            .catch(reason => {
                document.getElementById("reaccom-message").innerText = "Connection to the backend timed out";
            })
        ;
    }

    return (
        <div>
            <h1 data-testid="headerTitle">
                Improvement Kata
            </h1>
            <textarea data-testid="fieldAwesome"></textarea>
            <textarea data-testid="fieldNow"></textarea>
            <br/>
            <textarea data-testid="fieldNext"></textarea>
            <textarea data-testid="fieldBreakdown"></textarea>
            <br/>
            <button data-testid="insertButton" onClick={handleInsertButtonClick}>
                Insert
            </button>
            <br/>
            <div id="reaccom-message" data-testid="message"></div>
            <hr/>
            <ul data-testid="versionsList"></ul>
        </div>
    )
}

ReactDOM.render(<App/>, document.getElementById("root"));
