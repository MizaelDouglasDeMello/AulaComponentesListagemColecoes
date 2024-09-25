package com.example.aulacomponenteslistagemcolecoes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.aulacomponenteslistagemcolecoes.databinding.ItemCardviewBinding

class MensagemAdapter(
    private val clique: (String) -> Unit
) : Adapter<MensagemAdapter.MensagemViewHolder>() {

    private var listaMensagens = mutableListOf<Mensagem>()

    fun executarOperacao(){

        listaMensagens.removeAt(1)
        listaMensagens.removeAt(2)
        notifyItemRangeRemoved(1, 2)
        //notifyItemRemoved(1)
    }

    fun atualizarListaDados( lista: MutableList<Mensagem> ){
        //listaMensagens.addAll( lista )
        listaMensagens = lista
        notifyDataSetChanged()
    }

    inner class MensagemViewHolder(
        val binding: ItemCardviewBinding
    ) : ViewHolder( binding.root )  {

        fun bind( mensagem: Mensagem ){//Conectar com a interface

            binding.textCardNome.text = mensagem.nome
            binding.textCardUltima.text = mensagem.ultima

            //Aplicar eventos de clique
            binding.cardViewLayout.setOnClickListener {
                clique( mensagem.nome )
            }
        }

    }

    //Ao Criar o View Holder -> Criar a visualização
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MensagemViewHolder {

        val layoutInflater = LayoutInflater.from(
            parent.context
        )


        val itemView = ItemCardviewBinding.inflate(
            layoutInflater, parent, false
        )

        return MensagemViewHolder( itemView )

    }


    // ao vincular os dados para a visualização
    override fun onBindViewHolder(holder: MensagemViewHolder, position: Int) {
        val mensagem = listaMensagens[position]
        holder.bind( mensagem )
    }

    //getItemCount -> Recuperar a quantidade de itens
    override fun getItemCount(): Int {
        return listaMensagens.size
    }

}