```python
# insecure_example.py
"""
This file intentionally contains obvious security flaws
that Bandit should detect.
DO NOT use in production.
"""

import os
import subprocess
import pickle
import hashlib
import random
import tempfile
import yaml
from flask import Flask, request

app = Flask(__name__)

# Hardcoded password (Bandit: B105)
ADMIN_PASSWORD = "supersecret123"

# Weak cryptography (Bandit: B303)
def weak_hash(password):
    return hashlib.md5(password.encode()).hexdigest()

# Unsafe YAML loading (Bandit: B506)
def load_config(path):
    with open(path, "r") as f:
        return yaml.load(f, Loader=yaml.Loader)

# Insecure random token generation (Bandit: B311)
def generate_token():
    return str(random.random())

# Dangerous pickle deserialization (Bandit: B301)
def deserialize_user(data):
    return pickle.loads(data)

# Shell injection vulnerability (Bandit: B602)
def ping_host(host):
    cmd = f"ping -c 1 {host}"
    return subprocess.check_output(cmd, shell=True)

# Using eval on user input (Bandit: B307)
def calculate(expression):
    return eval(expression)

# Binding to all interfaces (Bandit may warn depending on config)
@app.route("/run")
def run():
    user_command = request.args.get("cmd")

    # Another command injection flaw
    os.system(user_command)

    return "Command executed"

# Predictable temporary file usage (Bandit: B306)
def temp_file():
    name = tempfile.mktemp()
    with open(name, "w") as f:
        f.write("temporary data")
    return name

# Assert used for security check (Bandit: B101)
def authenticate(user, password):
    assert password == ADMIN_PASSWORD
    return True

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000, debug=True)
```
