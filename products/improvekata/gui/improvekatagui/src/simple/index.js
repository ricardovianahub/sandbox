const reactContentRoot = document.getElementById("root");

const App = () => {
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
            <button data-testid="insertButton">
                Insert
            </button>
        </div>
    )
}

ReactDOM.render(<App/>, reactContentRoot);
