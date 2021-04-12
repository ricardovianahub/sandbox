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
                        <button data-testid="insertButton" onClick={this.handleInsertButtonClick}>Insert</button>
                        <button data-testid="deleteButton" onClick={this.handleDeleteButtonClick}>Delete</button>
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

    render() {
        const list = [];
        for (let row of this.state.rows) {
            list.push(
                <li key={row.uniqueId} uniqueid={row.uniqueId} onClick={() => this.handleLiAnchorClick(row.uniqueId)}>
                    {moment(row.createdAt).format("YYYY-MM-DD hh:mm:ss")}
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
