document.getElementById('botao-enviar').addEventListener('click', enviarMensagem);
document.getElementById('user-input').addEventListener('keypress', function(e) {
    if (e.key === 'Enter') {
        enviarMensagem();
    }
});

function enviarMensagem() {
    const userInput = document.getElementById('user-input');
    const chave_acesso = document.getElementById('chave-acesso').value.trim();
    const mensagem = userInput.value.trim();
    if (mensagem === '') return;

    adicionarMensagem('user', mensagem);
    userInput.value = '';

    fetch('api/chat', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            prompt: mensagem,
            chave_acesso: chave_acesso
        })
    })
    .then(response => {
        if (!response.ok) {
            return response.json().then(errorData => {
                throw new Error(errorData.erro || "Erro desconhecido.");
            });
        }
        return response.json();
    })
    .then(data => {
        adicionarMensagem('bot', data.resposta);
    })
    .catch(error => {
        adicionarMensagem('bot', error.message);
    });
}

function adicionarMensagem(remetente, mensagem) {
    const chatMensagens = document.getElementById('chat-mensagens');
    const elementoMensagem = document.createElement('div');
    elementoMensagem.classList.add('mensagem', remetente);
    const formattedMessage = mensagem
        .replace(/\*\*(.*?)\*\*/g, '<strong>$1</strong>')
        .replace(/\n/g, '<br>');
    elementoMensagem.innerHTML = `<div class="mensagem-conteudo">${formattedMessage}</div>`;
    chatMensagens.appendChild(elementoMensagem);
    chatMensagens.scrollTop = chatMensagens.scrollHeight;
}

