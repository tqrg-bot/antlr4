/*
 [The "BSD license"]
 Copyright (c) 2011 Terence Parr
 All rights reserved.

 Redistribution and use in source and binary forms, with or without
 modification, are permitted provided that the following conditions
 are met:

 1. Redistributions of source code must retain the above copyright
    notice, this list of conditions and the following disclaimer.
 2. Redistributions in binary form must reproduce the above copyright
    notice, this list of conditions and the following disclaimer in the
    documentation and/or other materials provided with the distribution.
 3. The name of the author may not be used to endorse or promote products
    derived from this software without specific prior written permission.

 THIS SOFTWARE IS PROVIDED BY THE AUTHOR ``AS IS'' AND ANY EXPRESS OR
 IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.
 IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY DIRECT, INDIRECT,
 INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT
 NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF
 THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.antlr.v4.runtime;

import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.IntervalSet;
import org.antlr.v4.runtime.misc.NotNull;

public class DiagnosticErrorListener extends BaseErrorListener<Token> {
    @Override
    public void reportAmbiguity(@NotNull Parser recognizer,
								DFA dfa, int startIndex, int stopIndex, @NotNull IntervalSet ambigAlts,
								@NotNull ATNConfigSet configs)
    {
		String format = "reportAmbiguity d=%s: ambigAlts=%s:%s, input='%s'";
		recognizer.notifyErrorListeners(
			String.format(format,
						  getDecisionDescription(recognizer, dfa.decision),
						  ambigAlts,
						  getConfigSetDescription(configs),
						  recognizer.getInputString(startIndex, stopIndex)));
    }

	@Override
	public void reportAttemptingFullContext(@NotNull Parser recognizer,
											@NotNull DFA dfa,
											int startIndex, int stopIndex,
											@NotNull ATNConfigSet configs)
	{
		String format = "reportAttemptingFullContext d=%d: %s, input='%s'";
		recognizer.notifyErrorListeners(
			String.format(format,
						  getDecisionDescription(recognizer, dfa.decision),
						  getConfigSetDescription(configs),
						  recognizer.getInputString(startIndex, stopIndex)));
	}

	@Override
	public void reportContextSensitivity(@NotNull Parser recognizer, @NotNull DFA dfa,
                                         int startIndex, int stopIndex, @NotNull ATNConfigSet configs)
    {
		String format = "reportContextSensitivity d=%d: %s, input='%s'";
		recognizer.notifyErrorListeners(
			String.format(format,
						  getDecisionDescription(recognizer, dfa.decision),
						  getConfigSetDescription(configs),
						  recognizer.getInputString(startIndex, stopIndex)));
    }

	protected String getDecisionDescription(Parser recognizer, int decision) {
		return Integer.toString(decision);
	}

	protected String getConfigSetDescription(ATNConfigSet configs) {
		return configs.toString();
	}
}
