# Backend

This is a simple spring boot backend that serves a single endpoint /date

# DateController /date 
When calling /date with a GET request it returns a simple DateResponse object holding an encrypted date fx:
{
    date: 8VgM1cMPvcGNlAiTiVREMGSWjaTAct9vuU18vXAObAY=
}

The date is generated/updated every minut in DateGenerator using springs @Scheduled annotation and is available via the getDate method.
After getting the latest date, the date is encrypted and the encrypted date is parsed into the new DateRespone and retuned

# EncryptionUtil
EncryptionUtil hold two public metohds for encryption and decryption of string using AES
