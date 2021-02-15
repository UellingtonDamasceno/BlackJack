
package br.uefs.ecomp.blackjack.alltests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import br.uefs.ecomp.blackjack.controller.*;
import br.uefs.ecomp.blackjack.model.*;
import br.uefs.ecomp.blackjack.util.*;
/**
 *
 * @author Uellington Damasceno
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
	ListaEncadeadaTest.class,
        PilhaTest.class,
        BaralhoTest.class,
	CartaTest.class,
        CroupierTest.class,
	JogadorTest.class,
        MaoDeCartaTest.class,
        PartidaTest.class,
})
public class AllTest { }
