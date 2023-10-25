package com.example.registelogin_actionbar_tablayout

import android.content.Intent
import android.os.Bundle
import android.text.Html
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.registelogin_actionbar_tablayout.databinding.FragmentRegisterBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RegisterFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RegisterFragment : Fragment(R.layout.fragment_register) {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private var _binding:  FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment LoginFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            LoginFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root
        val checkBox = binding.checkBox
        val tac = "<font color=#FF000000>By checking the box you agree to our</font> " +
                "<font color=#525BFF>Terms</font> " +
                "<font color=#FF000000>and</font> " +
                "<font color=#525BFF>Conditions.</font>"
        checkBox.text = Html.fromHtml(tac)

        with(binding) {

            btnRegister.setOnClickListener{
                if(editTxtName.text!!.isEmpty()) {
                    editTxtName.error = "Mohon Isi Username"
                } else if(editTxtEmail.text!!.isEmpty()) {
                    editTxtEmail.error = "Mohon Isi Email"
                } else if(editTxtPhone.text!!.isEmpty()) {
                    editTxtPhone.error = "Mohon Isi Nomor Telepon Anda"
                } else if(editTxtPassword.text!!.isEmpty()) {
                    editTxtPassword.error = "Mohon Isi Password"
                } else if(!checkBox.isChecked) {
                    (requireActivity() as? MainActivity)?.makeToast("Mohon Setujui Syarat dan Ketentuan")
                } else {
                    editTxtName.text!!.clear()
                    editTxtEmail.text!!.clear()
                    editTxtPhone.text!!.clear()
                    editTxtPassword.text!!.clear()

                    (requireActivity() as? MainActivity)?.makeToast("Akun Berhasil Terbuat")
                    val intent = Intent(this@RegisterFragment.requireActivity(), DashboardActivity::class.java)
                    startActivity(intent)
                }
            }

            txtNewMember.setOnClickListener{
                (requireActivity() as? MainActivity)?.switchFragment(1)
            }
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}