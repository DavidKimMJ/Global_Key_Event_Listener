package globalKeyDet;

import java.util.HashMap;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;

public class GlobalKeyDet implements NativeKeyListener {
	
	private HashMap<Integer, Boolean> map = new HashMap<Integer, Boolean>();
	
	public static void main(String[] args) {
		try {
			GlobalScreen.registerNativeHook();
		} catch (NativeHookException e) {
			System.err.println("There was a problem registering the native hook;");
			System.err.println(e.getMessage());
			
			System.exit(1);
		}
		GlobalScreen.addNativeKeyListener(new GlobalKeyDet());
	}
	
	public void nativeKeyPressed(NativeKeyEvent nke) {
		if (map.get(nke.getKeyCode()) == null || map.get(nke.getKeyCode()) == false) {
			System.out.println("Key Pressed: " + NativeKeyEvent.getKeyText(nke.getKeyCode()));
			map.put(nke.getKeyCode(), true);
		}
		
		if (nke.getKeyCode() == NativeKeyEvent.VC_ESCAPE) {
			try {
				GlobalScreen.unregisterNativeHook();
			} catch (NativeHookException e) {
				System.err.println("An error has occurred while unregistering the native hook;");
				System.err.println(e.getMessage());
				
				System.exit(1);
			}
		}
	}
	
	public void nativeKeyReleased(NativeKeyEvent nke) {
		System.out.println("Key Released: " + NativeKeyEvent.getKeyText(nke.getKeyCode()));
		map.remove(nke.getKeyCode());
	}
	
	/* Delete the code below; the code < NativeKeyEvent.getKeyText(nke.getKeyChar()) > from the method nativeKeyTyped does not generate a character matching with the key typed; the key list is not synchronized with the qwerty keyboard.
	
	public void nativeKeyTyped(NativeKeyEvent nke) {
		System.out.println("Key Typed: " + NativeKeyEvent.getKeyText(nke.getKeyChar() - 68));
	}
	*/
	
}