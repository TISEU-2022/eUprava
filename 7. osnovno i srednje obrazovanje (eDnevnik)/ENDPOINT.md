### We recieve:
**GET** /api/ucenik/_**{jmbg}**_/diploma/_**{tip_ustanove}**_
* JMBG: 13 in length, no more, no less
* Tip ustanove: "osnovna" v "srednja"

### You receive:
200 OK - Good request
```JSON
{
    "ime_ucenika": "",
    "prezime_ucenika": "",
    "jmbg": "1234567981234",
    "ime_ustanove": "",
    "tip_ustanove": "", <!-- "osnovna" v "srednja" -->
    "prosek": [
            "1": 1.23,
            "2": 1.23,
            "3": 1.23,
            "4": 1.23,
        ]
}
```
OR

404 Not Found - Bad request
