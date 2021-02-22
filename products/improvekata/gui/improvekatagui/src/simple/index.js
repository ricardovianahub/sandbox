var endPoint = '/ben/monitor';

function setEndpoint(value) {
    endPoint = value;
}

const App = () => {
    function handleInsertButtonClick() {
        axios.get(endPoint)
            .then(response => {
                let timestamp = new Date();
                var improvementGrid = "{\"teamName\":\"" + document.querySelectorAll("[data-testid=\"versionsList\"]")[0] + "\"," +
                    "\"teamName\":\"DOD_REACCOM\",\"field1Awesome\":\"field1\"," +
                    "\"title\":\"" + timestamp + "\",\"field1Awesome\":\"field1\"," +
                    "\"field2Now\":\"field2\"," +
                    "\"field3Next\":\"field3\"," +
                    "\"field4Breakdown\":\"field4\"}";
                const options = {
                    headers: {'Content-Type': 'application/json'}
                };
                axios.post('/ben/insert', improvementGrid, options);
                document.getElementById("reaccom-message").innerText = "Record inserted succesfully";
                document.querySelectorAll("[data-testid=\"versionsList\"]").forEach((ul_versionsList) => {
                    let li: Element = document.createElement("li");
                    let text: Element = document.createTextNode(timestamp);
                    li.appendChild(text);
                    ul_versionsList.appendChild(li);
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
            <input type="text" data-testid="title"></input>
            <br/>
            <hr/>
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

let handleLiAnchorClick = (title) => {
    alert("Hello! You have clicked\n" + title);
}

axios.get('/ben/queryAll')
    .then(response => {
        for (let row of response.data) {
            document.querySelectorAll("[data-testid=\"versionsList\"]").forEach((ul) => {
                let anchor: Element = document.createElement("a");
                let li: Element = document.createElement("li");
                let text: Element = document.createTextNode(row.title);
                anchor.setAttribute("href", "");
                anchor.setAttribute("onclick", "handleLiAnchorClick('" + row.title + "'); return false");
                anchor.appendChild(text);
                li.appendChild(anchor);
                ul.appendChild(li);
            })
        }
    });


ReactDOM.render(<App/>, document.getElementById("root"));
