var endPoint = '/ben/monitor';

function setEndpoint(value) {
    endPoint = value;
}

const App = () => {
    function handleInsertButtonClick() {
        axios.get(endPoint)
            .then(response => {
                var improvementGrid = "{\"teamName\":\""  + document.querySelectorAll("[data-testid=\"versionsList\"]")[0] + "\",\"title\":\"" +
                "Look ma no hands" +
                "\",\"field1Awesome\":\"field1\",\"field2Now\":\"field2\",\"field3Next\":\"field3\",\"field4Breakdown\":\"field4\"}";
                axios.post('/ben/insert', improvementGrid);
                document.getElementById("reaccom-message").innerText = "Record inserted succesfully";
                document.querySelectorAll("[data-testid=\"versionsList\"]").forEach((versionsList) => {
                    let node: Element = document.createElement("li");
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

axios.get('/ben/queryAll')
    .then(response => {
        for (let row of response.data) {
            document.querySelectorAll("[data-testid=\"versionsList\"]").forEach((ul) => {
                let anchor: Element = document.createElement("a");
                let li: Element = document.createElement("li");
                let text: Element = document.createTextNode(row.title);
                anchor.setAttribute("href", "");
                anchor.setAttribute("onclick", "alert('boo'); return false");
                anchor.appendChild(text);
                li.appendChild(anchor);
                ul.appendChild(li);
            })
        }
    });


ReactDOM.render(<App/>, document.getElementById("root"));
