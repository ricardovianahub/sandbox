const App = () => {
    function handleInsertButtonClick() {
        axios.get('/ben/monitor')
            .then(response => {
                document.getElementById("reaccom-message").innerText = "Record inserted succesfully";
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
        </div>
    )
}

ReactDOM.render(<App/>, document.getElementById("root"));
