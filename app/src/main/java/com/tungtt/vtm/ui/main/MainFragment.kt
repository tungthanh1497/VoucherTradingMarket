package com.tungtt.vtm.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.tungtt.vtm.MainActivity
import com.tungtt.vtm.databinding.MainFragmentBinding
import com.tungtt.vtm.model.UserModel

class MainFragment : Fragment() {

    companion object {
        fun newInstance(bundle: Bundle?): MainFragment {
            val mainFragment = MainFragment()
            mainFragment.arguments = bundle
            return mainFragment
        }
    }

    private lateinit var viewModel: MainViewModel
    private var userModel: UserModel? = null
    private var _binding: MainFragmentBinding? = null
    private lateinit var mGoogleSignInClient: GoogleSignInClient

    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userModel = arguments?.getParcelable(MainActivity.BUNDLE_USER_MODEL)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        init()
    }

    private fun init() {
        binding.tvUser.text = userModel.toString()
        initGoogle()
        implementListeners()
    }

    private fun initGoogle() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()
        mGoogleSignInClient = activity?.let { GoogleSignIn.getClient(it, gso) }!!
    }

    private fun implementListeners() {
        binding.ivSignOut.setOnClickListener { onSignOutGoogle() }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun onSignOutGoogle() {
        activity?.let {
            mGoogleSignInClient.signOut()
                .addOnCompleteListener(it) {
                    Toast.makeText(context, "sign out", Toast.LENGTH_LONG).show()
                }
        }
        activity?.finish()
    }

}