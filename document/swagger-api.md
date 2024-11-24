openapi: 3.0.0
servers:
  # Added by API Auto Mocking Plugin
  - description: SwaggerHub API Auto Mocking
    url: localhost/credit-card-reminder/1.0/
info:
  description: This is a simple API
  version: "1.0.0"
  title: Simple Inventory API
  contact:
    email: you@your-company.com
  license:
    name: Apache 2.0
    url: 'http://www.apache.org/licenses/LICENSE-2.0.html'
tags:
  - name: admins
    description: Secured Admin-only calls
  - name: developers
    description: Operations available to regular developers
paths:
  /transaction:
    get:
      tags:
        - developers
      summary: Insert transaction
      operationId: insertTransaction
      description: |
        By passing in the appropriate options, you can search for
        available inventory in the system
      parameters:
        - in: query
          name: searchString
          description: pass an optional search string for looking up inventory
          required: false
          schema:
            type: string
        - in: query
          name: skip
          description: number of records to skip for pagination
          schema:
            type: integer
            format: int32
            minimum: 0
        - in: query
          name: limit
          description: maximum number of records to return
          schema:
            type: integer
            format: int32
            minimum: 0
            maximum: 50
      responses:
        '200':
          description: search results matching criteria
          content:
            application/json:
              schema:
                type: array
                items:
                  $ref: '#/components/schemas/InventoryItem'
        '400':
          description: bad input parameter
    post:
      tags:
        - admins
      summary: adds a transaction
      operationId: addTransaction
      description: Adds an transaction to remind
      responses:
        '201':
          description: item created
        '400':
          description: 'invalid input, object invalid'
      requestBody:
        content:
          application/json:
            schema:
              $ref: '#/components/schemas/InventoryItem'
        description: Inventory item to add
components:
  schemas:
    InventoryItem:
      type: object
      required:
        - id
        - name
        - manufacturer
        - releaseDate
      properties:
        id:
          type: string
          format: uuid
          example: d290f1ee-6c54-4b01-90e6-d701748f0851
        name:
          type: string
          example: Widget Adapter
        releaseDate:
          type: string
          format: date-time1
          example: '2016-08-29T09:12:33.001Z'
        manufacturer:
          $ref: '#/components/schemas/Transaction'
    Transaction:
      required:
        - creditCardNumber
        - cardTypeId
        - userName
      properties:
        creditCardNumber:
          type: string
          example: 123456
        cardTypeId:
          type: integer
          example: 1
        userName:
          type: string
          format: string
          example: ntk
        ownerName:
          type: string
          example: NGUYEN TRUNG KHIET
        amount:
          type: integer
          example: 10000
        transactionDate:
          type: string
          example: dd/mm/yyyy
      type: object