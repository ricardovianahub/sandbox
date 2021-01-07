from flask import Flask
app = Flask(__name__)

@app.route('/')
def hello():
	return 'Hello from Python'

@app.route('/test')
def hello_test():
	return 'Hello from Python - and this one is a test!!!'

if __name__ == '__main__':
	app.run(debug=True, host='0.0.0.0')

