-- Primeiro o Quiz
INSERT INTO tb_quiz (id, title) VALUES (1, 'Quiz Museu da Bolsa do Brasil');

-- Depois as Questions
INSERT INTO tb_question (id, text, time_limit_sec, answer_id, quiz_id) VALUES
(1, 'O que é o MUB3 - Museu da Bolsa do Brasil?', 15, NULL, 1),
(2, 'Qual a diferença entre valor e preço?', 15, NULL, 1),
(3, 'O que eram os pregões nas bolsas de valores?', 15, NULL, 1),
(4, 'O que são ações?', 15, NULL, 1),
(5, 'O que significa negociação na bolsa de valores?', 15, NULL, 1);

-- Agora as Alternatives
INSERT INTO tb_alternative (id, text, format, question_id) VALUES
(1, 'Um banco de investimentos', 'TRIANGLE', 1),
(2, 'Um museu que preserva memórias do mercado de capitais', 'CIRCLE', 1),
(3, 'Uma corretora de ações', 'SQUARE', 1),
(4, 'Um órgão regulador do mercado', 'DIAMOND', 1),

(5, 'Não existe diferença, são a mesma coisa', 'TRIANGLE', 2),
(6, 'Valor é a importância atribuída, preço é a quantia em dinheiro', 'CIRCLE', 2),
(7, 'Preço é sempre maior que o valor', 'SQUARE', 2),
(8, 'Valor é apenas usado em matemática', 'DIAMOND', 2),

(9, 'Locais de negociação onde ações recebiam preço', 'TRIANGLE', 3),
(10, 'Reuniões de empresas com investidores', 'CIRCLE', 3),
(11, 'Palestras educativas sobre economia', 'SQUARE', 3),
(12, 'Salas de aula de mercado financeiro', 'DIAMOND', 3),

(13, 'Dinheiro usado no pregão', 'TRIANGLE', 4),
(14, 'Partes de uma empresa que podem ser compradas e vendidas', 'CIRCLE', 4),
(15, 'Taxas cobradas pela bolsa', 'SQUARE', 4),
(16, 'Documentos usados em auditorias', 'DIAMOND', 4),

(17, 'Conversa para definir preço das ações', 'TRIANGLE', 5),
(18, 'Processo de arquivar documentos', 'CIRCLE', 5),
(19, 'Atividade de museus financeiros', 'SQUARE', 5),
(20, 'Reunião de banco central', 'DIAMOND', 5);

-- Por fim, atualize as respostas corretas
UPDATE tb_question SET answer_id = 2 WHERE id = 1;
UPDATE tb_question SET answer_id = 6 WHERE id = 2;
UPDATE tb_question SET answer_id = 9 WHERE id = 3;
UPDATE tb_question SET answer_id = 14 WHERE id = 4;
UPDATE tb_question SET answer_id = 17 WHERE id = 5;

DROP TABLE IF EXISTS tb_alternative CASCADE;
DROP TABLE IF EXISTS tb_museum_data CASCADE;
DROP TABLE IF EXISTS tb_question CASCADE;
DROP TABLE IF EXISTS tb_quiz_questions CASCADE;
DROP TABLE IF EXISTS tb_quiz CASCADE;
