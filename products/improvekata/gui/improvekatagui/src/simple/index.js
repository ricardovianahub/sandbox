let endPointMonitor = '/ben/monitor';
let endpointInsert = '/ben/insert';

function setEndpointInsert(value) {
    endpointInsert = value;
}

function setEndpointMonitor(value) {
    endPointMonitor = value;
}

const App = () => {
    function handleDeleteButtonClick() {
        const uniqueId = document.getElementById("uniqueId").value;
        axios.delete('/ben/deleteByUniqueId/' + uniqueId)
            .then(deleteResponse => {
                document.getElementById("reaccom-message").innerText = "Record deleted successfully";
                document.getElementById("title").value = "";
                document.getElementById("field1Awesome").value = "";
                document.getElementById("field2Now").value = "";
                document.getElementById("field3Next").value = "";
                document.getElementById("field4Breakdown").value = "";
                document.querySelector("ul[id=versionsList] > li[uniqueId='"
                    + document.getElementById("uniqueId").value + "']").remove();
                document.getElementById("uniqueId").value = "";
            });
    }

    function handleInsertButtonClick() {
        axios.get(endPointMonitor)
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
                axios.post(endpointInsert, improvementGrid, options)
                    .then(insertResponse => {
                        document.getElementById("reaccom-message").innerText = "Record inserted successfully";
                        document.getElementById("uniqueId").value = insertResponse.data.uniqueId;
                        add1LineToVersionsList(insertResponse.data);
                    })
                    .catch(reason => {
                        document.getElementById("reaccom-message").innerText = "Connection to the backend timed out";
                    });
            })
            .catch(reason => {
                document.getElementById("reaccom-message").innerText = "Connection to the backend timed out";
            })
        ;
    }

    return (
        <div>
            <div className="centralize">
                <h1 data-testid="headerTitle">
                    Improvement Kata
                </h1>
                <input type="text" id="title" data-testid="title"/>
            </div>
            <hr/>
            <div className="wrapper">
                <div className="float">
                    <div className="float">
                        <div data-testid="fieldAwesomeLabel">Awesome</div>
                        <textarea id="field1Awesome" data-testid="fieldAwesome"/>
                    </div>
                    <div className="float">
                        <div data-testid="fieldNowLabel">Now</div>
                        <textarea id="field2Now" data-testid="fieldNow"/>
                    </div>
                    <br/>
                    <div className="float">
                        <div data-testid="fieldNextLabel">Next</div>
                        <textarea id="field3Next" data-testid="fieldNext"/>
                    </div>
                    <div className="float">
                        <div data-testid="fieldBreakdownLabel">Breakdown</div>
                        <textarea id="field4Breakdown" data-testid="fieldBreakdown"/>
                    </div>
                    <br/>
                    <button data-testid="insertButton" onClick={handleInsertButtonClick}>Insert</button>
                    <button data-testid="deleteButton" onClick={handleDeleteButtonClick}>Delete</button>
                    <div id="reaccom-message" data-testid="message"></div>
                </div>
                <div className="float">
                    <ul id="versionsList" data-testid="versionsList"></ul>
                </div>
                <input id="uniqueId" type="hidden" data-testid="uniqueId" />
            </div>
        </div>
    )
}

let handleLiAnchorClick = (uniqueId) => {
    axios.get('/ben/queryByUniqueId/' + uniqueId)
        .then(response => {
            for (let row of response.data) {
                document.getElementById("title").value = row.title;
                document.getElementById("field1Awesome").value = row.field1Awesome;
                document.getElementById("field2Now").value = row.field2Now;
                document.getElementById("field3Next").value = row.field3Next;
                document.getElementById("field4Breakdown").value = row.field4Breakdown;
                document.getElementById("uniqueId").value = row.uniqueId;
            }
        });
}

function add1LineToVersionsList(row) {
    document.querySelectorAll("[data-testid=\"versionsList\"]").forEach((ul) => {
        let anchor = document.createElement("a");
        let li = document.createElement("li");
        li.setAttribute("uniqueId", row.uniqueId);
        let text = document.createTextNode(moment(row.createdAt).format("YYYY-MM-DD hh:mm:ss"));
        anchor.setAttribute("href", "");
        anchor.setAttribute("onclick", "handleLiAnchorClick('" + row.uniqueId + "'); return false");
        anchor.appendChild(text);
        li.appendChild(anchor);
        ul.appendChild(li);
    })
}

axios.get('/ben/queryAll')
    .then(response => {
        for (let row of response.data) {
            add1LineToVersionsList(row);
        }
    });

ReactDOM.render(<App/>, document.getElementById("root"));
