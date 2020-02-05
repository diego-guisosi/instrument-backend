Modelo de dados
Tabela Instrument (TBL_INSTR)
	Identifier (INSTR_ID) PK
	Symbol (INSTR_SYMBOL)
	Maturity Date (INSTR_MAT_DATE)

Tabela Historical Price (TBL_HIST_PRICE)
	Instrument Identifier (INSTR_ID) PK / FK (TBL_INSTR.INSTR_ID)
	Reference Date (HIST_PRICE_ID) PK
	Value (HIST_PRICE_VALUE)

1. BUG: A listagem da tela "Instruments" não está apresentando a data de vencimento dos instrumentos na coluna Maturity Date. Corrigir a tela, de forma que as datas passem a ser apresentadas.

2. TASK: Implementar um novo endpoint que retorne o maior e mais recente (no caso de haver preços repetidos) preço armazenado no banco de dados para um símbolo de instrumento fornecido. Este endpoint será consumido futuramente pela tela "Instruments", que passará a apresentar o valor retornado na coluna "Highest Historical Price".
2.1. Premissa: O preço retornado não pode ter data anterior a um ano
2.2. Observações: 
2.2.1. Não é necessário resolver este item exclusivamente com consulta de banco. Se julgar necessário, pode tratar parte dos requisitos no backend com Java. Lembre-se apenas que a tabela "Historical Price" contem dezenas de milhares de preços para cada símbolo de instrumento.
2.2.2. Não é necessário alterar o frontend. Mas, se quiser implementar o consumo do novo serviço, de forma que o preço seja apresentado em tela, ganhará mais pontos.

3. BÔNUS (Apenas se a tarefa 2 estiver implementada): Modificar a implementação do endpoint que consulta todos os preços, para que o mesmo passe a retornar os preços da tabela Historical Price em vez de retornar do arquivo "prices.json"



