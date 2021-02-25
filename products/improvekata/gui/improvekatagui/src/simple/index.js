var endPoint = '/ben/monitor';

function setEndpoint(value) {
    endPoint = value;
}

const App = () => {
    function handleInsertButtonClick() {
        axios.get(endPoint)
            .then(response => {
                let timestamp = new Date();
                var improvementGrid = "{\"teamName\":\"" + document.getElementById("versionsList") + "\"," +
                    "\"teamName\":\"DOD_REACCOM\"," +
                    "\"title\":\"" + document.getElementById("title").value + "\"," +
                    "\"field1Awesome\":\"" + document.getElementById("field1Awesome").value + "\"," +
                    "\"field2Now\":\"" + document.getElementById("field2Now").value + "\"," +
                    "\"field3Next\":\"" + document.getElementById("field3Next").value + "\"," +
                    "\"field4Breakdown\":\"" + document.getElementById("field4Breakdown").value + "\"}";
                const options = {
                    headers: {'Content-Type': 'application/json'}
                };
                axios.post('/ben/insert', improvementGrid, options)
                    .then(insertResponse => {
                        document.getElementById("reaccom-message").innerText = "Record inserted succesfully";
                        let li: Element = document.createElement("li");
                        let text: Element = document.createTextNode(timestamp);
                        li.appendChild(text);
                        document.getElementById("versionsList").appendChild(li);
                    });
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
            <input type="text" id="title" data-testid="title"/>
            <br/>
            <hr/>
            <textarea id="field1Awesome" data-testid="fieldAwesome"/>
            <textarea id="field2Now" data-testid="fieldNow"/>
            <br/>
            <textarea id="field3Next" data-testid="fieldNext"/>
            <textarea id="field4Breakdown" data-testid="fieldBreakdown"/>
            <br/>
            <button data-testid="insertButton" onClick={handleInsertButtonClick}>
                Insert
            </button>
            <br/>
            <div id="reaccom-message" data-testid="message"></div>
            <hr/>
            <ul id="versionsList" data-testid="versionsList"></ul>
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
                let text: Element = document.createTextNode(row.createdAt);
                anchor.setAttribute("href", "");
                anchor.setAttribute("onclick", "handleLiAnchorClick('" + row.uniqueId + "'); return false");
                anchor.appendChild(text);
                li.appendChild(anchor);
                ul.appendChild(li);
            })
        }
    });


ReactDOM.render(<App/>, document.getElementById("root"));
