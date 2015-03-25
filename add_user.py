import requests

r = requests.post(
    'http://localhost:9000/add_user',
    data={
        'curtin_id': '17690579',
        'password': 'burp'
    }
)
print(r)
if len(r.text) < 200:
    print(r.text)
