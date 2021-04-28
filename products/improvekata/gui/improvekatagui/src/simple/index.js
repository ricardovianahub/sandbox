let endPointMonitor = '/ben/monitor';
let endpointInsert = '/ben/insert';

function setEndpointInsert(value) {
    endpointInsert = value;
}

function setEndpointMonitor(value) {
    endPointMonitor = value;
}

const assignValueById = (id, value) => {
    document.getElementById(id).value = value;
}

const retrieveValueById = (id) => {
    return document.getElementById(id).value;
}

const enableButton = (id) => {
    const button = document.getElementById(id);
    button.disabled = false;
    button.setAttribute("class", "enabledButton");
}

const disableButton = (id) => {
    const button = document.getElementById(id);
    button.disabled = true;
    button.setAttribute("class", "disabledButton");
}

function queryAllVersionsList() {
    axios.get('/ben/queryAll')
        .then(response => {
            this.setState({rows: response.data});
        });
}

class App extends React.Component {

    constructor() {
        super();
    }

    render() {
        return (
            <div>
                <div className="centralize">
                    <h1 data-testid="headerTitle">
                        Improvement Kata
                    </h1>
                    <input type="text" id="title" data-testid="title" onChange={this.handleMandatoryFieldChange} />
                </div>
                <hr/>
                <div className="wrapper">
                    <div className="float">
                        <div className="float">
                            <div data-testid="fieldAwesomeLabel">Awesome
                            </div>
                            <textarea id="field1Awesome" data-testid="fieldAwesome" onChange={this.handleMandatoryFieldChange} />
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
                        <button data-testid="insertButton" id="insertButton" class="disabledButton"
                                onClick={this.handleInsertButtonClick}>Insert
                        </button>
                        <button data-testid="deleteButton" id="deleteButton"
                                onClick={this.handleDeleteButtonClick}>Delete
                        </button>
                        <button data-testid="resetButton">Reset
                        </button>
                        <div id="reaccom-message" data-testid="message"/>
                    </div>
                    <div className="float">
                        <VersionsList/>
                    </div>
                    <input id="uniqueId" type="hidden" data-testid="uniqueId"/>
                </div>
            </div>
        )
    }


    handleMandatoryFieldChange() {
        if (retrieveValueById("title").trim() === "" || retrieveValueById("field1Awesome").trim() === "") {
            disableButton("insertButton");
        } else {
            enableButton("insertButton");
        }
    }

    handleDeleteButtonClick() {
        const uniqueId = document.getElementById("uniqueId").value;
        axios.delete('/ben/deleteByUniqueId/' + uniqueId)
            .then(deleteResponse => {
                document.getElementById("reaccom-message").innerText = "Record deleted successfully";
                assignValueById("title", "");
                assignValueById("field1Awesome", "");
                assignValueById("field2Now", "");
                assignValueById("field3Next", "");
                assignValueById("field4Breakdown", "");
                assignValueById("title", "");
                queryAllVersionsList();
                assignValueById("uniqueId", "");

                disableButton("insertButton");
            });
    }

    handleInsertButtonClick() {
        const improvementGrid = JSON.stringify({
            teamName: "DOD_REACCOM",
            title: document.getElementById("title").value,
            field1Awesome: document.getElementById("field1Awesome").value,
            field2Now: document.getElementById("field2Now").value,
            field3Next: document.getElementById("field3Next").value,
            field4Breakdown: document.getElementById("field4Breakdown").value
        });
        const options = {
            headers: {'Content-Type': 'application/json'}
        };
        axios.post(endpointInsert, improvementGrid, options)
            .then(insertResponse => {
                document.getElementById("reaccom-message").innerText = "Record inserted successfully";
                document.getElementById("uniqueId").value = insertResponse.data.uniqueId;
                queryAllVersionsList();
                enableButton("deleteButton")
            })
            .catch(reason => {
                document.getElementById("reaccom-message").innerText = "Connection to the backend timed out";
            });
    }
}

class VersionsList extends React.Component {

    constructor(props) {
        super(props);
        this.state = {rows: []};
        queryAllVersionsList = queryAllVersionsList.bind(this);
    }

    componentDidMount() {
        queryAllVersionsList();
    }

    componentDidUpdate() {
        if (this.state.rows.length === 0) {
            disableButton("deleteButton")
            disableButton("insertButton");
        } else {
            enableButton("deleteButton");
        }
    }

    handleLiAnchorClick(uniqueId) {
        axios.get('/ben/queryByUniqueId/' + uniqueId)
            .then(response => {
                for (let row of response.data) {
                    assignValueById("title", row.title);
                    assignValueById("field1Awesome", row.field1Awesome);
                    assignValueById("field2Now", row.field2Now);
                    assignValueById("field3Next", row.field3Next);
                    assignValueById("field4Breakdown", row.field4Breakdown);
                    assignValueById("uniqueId", row.uniqueId);
                }
            });
    }

    handleXClick(uniqueId) {
        axios.delete('/ben/deleteByUniqueId/' + uniqueId)
            .then(deleteResponse => {
                document.getElementById("reaccom-message").innerText = "Record deleted successfully";
                if (uniqueId === document.getElementById("uniqueId").value) {
                    assignValueById("title", "");
                    assignValueById("field1Awesome", "");
                    assignValueById("field2Now", "");
                    assignValueById("field3Next", "");
                    assignValueById("field4Breakdown", "");
                    assignValueById("uniqueId", "");
                }
                queryAllVersionsList();
            });
    }

    render() {
        const list = [];
        for (let row of this.state.rows) {
            list.push(
                <li key={row.uniqueId} uniqueid={row.uniqueId}>
                    <span className="blueButton"
                          onClick={() => this.handleLiAnchorClick(row.uniqueId)}> {moment(row.createdAt).format("YYYY-MM-DD hh:mm:ss")}</span>
                    &nbsp;
                    <span className="redX" onClick={() => this.handleXClick(row.uniqueId)}>X</span>
                </li>
            )
        }
        return (
            <ul id="versionsList" data-testid="versionsList">
                {list}
            </ul>
        );
    }
}

ReactDOM.render(<App/>, document.getElementById("root"));
