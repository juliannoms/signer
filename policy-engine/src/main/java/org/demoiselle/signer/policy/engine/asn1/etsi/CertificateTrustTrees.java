/*
 * Demoiselle Framework
 * Copyright (C) 2016 SERPRO
 * ----------------------------------------------------------------------------
 * This file is part of Demoiselle Framework.
 *
 * Demoiselle Framework is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License version 3
 * along with this program; if not,  see <http://www.gnu.org/licenses/>
 * or write to the Free Software Foundation, Inc., 51 Franklin Street,
 * Fifth Floor, Boston, MA  02110-1301, USA.
 * ----------------------------------------------------------------------------
 * Este arquivo é parte do Framework Demoiselle.
 *
 * O Framework Demoiselle é um software livre; você pode redistribuí-lo e/ou
 * modificá-lo dentro dos termos da GNU LGPL versão 3 como publicada pela Fundação
 * do Software Livre (FSF).
 *
 * Este programa é distribuído na esperança que possa ser útil, mas SEM NENHUMA
 * GARANTIA; sem uma garantia implícita de ADEQUAÇÃO a qualquer MERCADO ou
 * APLICAÇÃO EM PARTICULAR. Veja a Licença Pública Geral GNU/LGPL em português
 * para maiores detalhes.
 *
 * Você deve ter recebido uma cópia da GNU LGPL versão 3, sob o título
 * "LICENCA.txt", junto com esse programa. Se não, acesse <http://www.gnu.org/licenses/>
 * ou escreva para a Fundação do Software Livre (FSF) Inc.,
 * 51 Franklin St, Fifth Floor, Boston, MA 02111-1301, USA.
 */

package org.demoiselle.signer.policy.engine.asn1.etsi;

import java.util.ArrayList;
import java.util.Collection;

import org.bouncycastle.asn1.ASN1Primitive;
import org.bouncycastle.asn1.ASN1Sequence;
import org.demoiselle.signer.policy.engine.asn1.ASN1Object;

/**
 * The certificateTrustTrees identifies a set of self signed certificates
 * for the trust points used to start (or end) certificate path processing
 * and the initial conditions for certificate path validation as defined RFC 2459 [6] clause 6.
 * This ASN1 structure is used to define policy for validating the signing certificate,
 * the TSA's certificate and attribute certificates
 *
 *  Collection&lt; CertificateTrustPoint &gt; @link CertificateTrustPoint
 *
 */
public class CertificateTrustTrees extends ASN1Object {

    private Collection<CertificateTrustPoint> certificateTrustPoints;

    public Collection<CertificateTrustPoint> getCertificateTrustPoints() {
        return certificateTrustPoints;
    }

    public void setCertificateTrustPoints(
            Collection<CertificateTrustPoint> certificateTrustPoints) {
        this.certificateTrustPoints = certificateTrustPoints;
    }

    @Override
    public void parse(ASN1Primitive derObject) {
        ASN1Sequence derSequence = ASN1Object.getDERSequence(derObject);
        int total = derSequence.size();
        for (int i = 0; i < total; i++) {
            CertificateTrustPoint certificateTrustPoint = new CertificateTrustPoint();
            certificateTrustPoint.parse(derSequence.getObjectAt(i).toASN1Primitive());
            if (this.certificateTrustPoints == null) {
                this.certificateTrustPoints = new ArrayList<CertificateTrustPoint>();
            }
            this.certificateTrustPoints.add(certificateTrustPoint);
        }
    }

}
