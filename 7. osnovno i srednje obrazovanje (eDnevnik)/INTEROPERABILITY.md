### We send identification_id to:
**GET** /api/service/**${identification_id}**/get-relations
* identification_id: 13 digits in length, no more, no less

### We receive:
200 OK 
```JSON
{
    "user": "0312999800942",
    "first_name": "Mirko",
    "last_name": "Mirkovic",
    "children": [
        {
            "first_name": "Marko",
            "last_name": "Markovic",
            "born_at": "03-07-1990",
            "deceased_at": null,
            "identification_id": "1234567891011"
        }
    ]
}
```
OR

404 Not Found - Bad request