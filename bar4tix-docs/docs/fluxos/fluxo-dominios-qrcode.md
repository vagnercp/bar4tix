flowchart LR

subgraph U ["Usuario"]
  A1["Joao (App/Web)"]
end

subgraph D1 ["Dominio Receipts"]
  A2["Leitura QR-Code"] --> A3["Envio para Fila Receipts"]
  A3 --> A4["Processamento Receita"]
  A4 --> A5["Recebido e Validado"]
end

subgraph D2 ["Dominio Ingestion"]
  B1["Upload Panfleto"]
  B1 --> B2["OCR/Extracao de Itens"]
  B2 --> B3["Itens Normalizados"]
end

subgraph D3 ["Dominio Pricing"]
  C1["Recebe eventos Receipts/Ingestion"]
  C1 --> C2["Compara precos atuais"]
  C2 --> C3["Atualiza banco se diferente"]
  C3 --> C4["Publica PriceUpdatedEvent"]
end

subgraph D4 ["Dominio Search/API"]
  D1["Recebe PriceUpdatedEvent"]
  D1 --> D2["Atualiza Cache/Read Models"]
  D2 --> D3["Disponibiliza nova consulta publica"]
end

subgraph D5 ["Dominio Analytics"]
  E1["Recebe logs de atualizacao"]
  E1 --> E2["Gera metricas agregadas"]
  E2 --> E3["Exportacoes / Paineis publicos"]
end

subgraph D6 ["Dominio Identity & Privacy"]
  F1["Hash anonimo do usuario"]
  F1 --> F2["Tabelas criptografadas: Identidade"]
  F2 --> F3["Mapeamento interno: user_hash ↔ CPF"]
end

A1 -->|QR-Code| A2
A1 -->|Panfleto| B1
A5 -->|Evento CupomProcessado| C1
B3 -->|Evento PanfletoProcessado| C1
C4 -->|Novo preco| D1
C4 -->|Log atualizacao| E1
A1 -->|Consulta autenticado| D3
D3 -->|Resumo pessoal| A1
F3 -.-> A1

style F2 fill:#ffe6cc,stroke:#ff6600,stroke-width:2px
style F3 fill:#ffd699,stroke:#ff6600,stroke-width:1px

